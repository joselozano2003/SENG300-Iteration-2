/** Members for Iteration 2:
 * Ethan Oke (30142180)
 * Jose Camilo Lozano Cetina (30144736)
 * Quinn Leonard (30145315)
 * Efren Garcia (30146181)
 * Nam Anh Vu (30127597)
 * Tyler Nguyen (30158563)
 * Victor Han (30112492)
 * Francisco Huayhualla (30091238)
 * Md Minhazur Rahman Hamim (30145446)
 * Imran Haji (30141571)
 * Sara Dhuka (30124117)
 * Robert (William) Engel (30119608)
 */
package com.autovend.software;
import java.math.BigDecimal;

import com.autovend.Card.CardData;
import com.autovend.devices.AbstractDevice;
import com.autovend.devices.CardReader;
import com.autovend.devices.SelfCheckoutStation;
import com.autovend.devices.SimulationException;
import com.autovend.devices.observers.AbstractDeviceObserver;
import com.autovend.devices.observers.CardReaderObserver;
import com.autovend.external.CardIssuer;

public class PayWithCard extends Pay implements CardReaderObserver {
	
	private BigDecimal amountToPay;
	private CardIssuer cardIssuer;

	
	
	public PayWithCard(SelfCheckoutStation station, CardIssuer cardIssuer) {
		super(station);
		BigDecimal amountToPay = PurchasedItems.getAmountLeftToPay();
		
		if (cardIssuer == null) {
            throw new SimulationException(new NullPointerException("No argument may be null."));
        }
		
		// Ensure that no change is produced when paying with card
		if (amountToPay.compareTo(super.getAmountDue().subtract(PurchasedItems.getAmountPaid())) > 0) {
			this.amountToPay = super.getAmountDue().subtract(PurchasedItems.getAmountPaid());
		} else this.amountToPay = amountToPay;

		this.cardIssuer = cardIssuer;
	}

	@Override
	public void reactToEnabledEvent(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reactToDisabledEvent(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reactToCardInsertedEvent(CardReader reader) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reactToCardRemovedEvent(CardReader reader) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reactToCardTappedEvent(CardReader reader) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reactToCardSwipedEvent(CardReader reader) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reactToCardDataReadEvent(CardReader reader, CardData data) {
		int holdNumber = cardIssuer.authorizeHold(data.getNumber(), PurchasedItems.getAmountLeftToPay()); 						  	// Contact card issuer and attempt to place a hold
		if (holdNumber == -1) return; 																		// Return if hold is unable to be placed
		boolean transactionPosted = cardIssuer.postTransaction(data.getNumber(), holdNumber, PurchasedItems.getAmountLeftToPay()); 	// Contact card issuer to attempt to post transaction
		if (transactionPosted) super.pay(PurchasedItems.getAmountLeftToPay()); 														// If transaction is posted, pay the amount
	}
}
