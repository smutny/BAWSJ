package pl.edu.amu.bawsj.atm2.model.manager;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import pl.edu.amu.bawsj.atm2.model.exception.*;
import pl.edu.amu.bawsj.atm2.model.rest.RestClient;
import pl.edu.amu.bawsj.atmjpa.model.*;

import java.math.BigDecimal;
import java.util.List;

public class AccountManager {
    private Client client = new RestClient().getClient();

    public BigDecimal checkMoneyInAccount(Card2 insertedCard) throws UnauthorizedException, UnknownResponseException {
        Card card = new Card(String.valueOf(insertedCard.getNumber()), insertedCard.getPin().digitsToString());

        WebResource resource = client.resource(RestClient.BASE_URI);
        ClientResponse response = resource.path("cards").path("balance").type("application/json")
                .post(ClientResponse.class, card);

        if (response.getStatus() == 200) {
            Balance responseBalance = response.getEntity(Balance.class);
            return new BigDecimal(responseBalance.getAmount());
        } else if (response.getStatus() == 401) {
            throw new UnauthorizedException();
        } else {
            throw new UnknownResponseException();
        }
    }

    public List<Note> withdrawMoneyFromAccount(Card2 insertedCard, int amount) throws NotEnoughMoneyInATMException, NotEnoughMoneyInAccountException, UnauthorizedException, WrongAmountToWithdrawException, UnknownResponseException {
        Withdraw withdraw = new Withdraw();
        withdraw.setNumber(String.valueOf(insertedCard.getNumber()));
        withdraw.setPin(insertedCard.getPin().digitsToString());
        withdraw.setAmount(amount);

        WebResource resource = client.resource(RestClient.BASE_URI);
        ClientResponse response = resource.path("atm").path("withdraw").type("application/json")
                .post(ClientResponse.class, withdraw);

        if (response.getStatus() == 200) {
            return response.getEntity(new GenericType<List<Note>>() {
            });
        } else if (response.getStatus() == 500) {
            throw new NotEnoughMoneyInATMException();
        } else if (response.getStatus() == 400) {
            throw new NotEnoughMoneyInAccountException();
        } else if (response.getStatus() == 406) {
            throw new WrongAmountToWithdrawException();
        } else if (response.getStatus() == 401) {
            throw new UnauthorizedException();
        } else {
            throw new UnknownResponseException();
        }
    }

    public BigDecimal payMoneyIntoAccount(Card2 insertedCard, List<Note> notes) throws UnauthorizedException, UnknownResponseException {
        Deposit deposit = new Deposit();
        deposit.setNumber(String.valueOf(insertedCard.getNumber()));
        deposit.setPin(insertedCard.getPin().digitsToString());
        deposit.setNotes(notes);

        WebResource resource = client.resource(RestClient.BASE_URI);
        ClientResponse response = resource.path("atm").path("deposit_money").type("application/json")
                .post(ClientResponse.class, deposit);

        if (response.getStatus() == 200) {
            return checkMoneyInAccount(insertedCard);
        } else if (response.getStatus() == 404) {
            throw new UnauthorizedException();
        } else if (response.getStatus() == 401) {
            throw new UnauthorizedException();
        } else {
            throw new UnknownResponseException();
        }
    }
}
