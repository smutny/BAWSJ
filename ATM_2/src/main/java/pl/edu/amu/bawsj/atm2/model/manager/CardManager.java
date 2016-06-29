package pl.edu.amu.bawsj.atm2.model.manager;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import pl.edu.amu.bawsj.atm2.model.exception.UnauthorizedException;
import pl.edu.amu.bawsj.atm2.model.exception.UnknownResponseException;
import pl.edu.amu.bawsj.atm2.model.rest.RestClient;
import pl.edu.amu.bawsj.atmjpa.model.Card2;
import pl.edu.amu.bawsj.atmjpa.model.NewPinCard;
import pl.edu.amu.bawsj.atmjpa.model.PIN;

public class CardManager {
    private Client client = new RestClient().getClient();

    public PIN changeCardPIN(Card2 insertedCard, PIN newPIN) throws UnauthorizedException, UnknownResponseException {
        NewPinCard newPinCard = new NewPinCard();
        newPinCard.setNumber(String.valueOf(insertedCard.getNumber()));
        newPinCard.setPin(insertedCard.getPin().digitsToString());
        newPinCard.setNewPin(newPIN.digitsToString());

        WebResource resource = client.resource(RestClient.BASE_URI);
        ClientResponse response = resource.path("cards").path("change-pin").type("application/json")
                .post(ClientResponse.class, newPinCard);

        if (response.getStatus() == 200) {
            return newPIN;
        } else if (response.getStatus() == 401) {
            throw new UnauthorizedException();
        } else {
            throw new UnknownResponseException();
        }
    }
}
