package com.autovend.software;
import java.math.BigDecimal;

import com.autovend.Card.CardData;
import com.autovend.devices.AbstractDevice;
import com.autovend.devices.CardReader;
import com.autovend.devices.SelfCheckoutStation;
import com.autovend.devices.observers.AbstractDeviceObserver;
import com.autovend.devices.observers.CardReaderObserver;
import com.autovend.external.CardIssuer;

public class PayWithCard extends Pay implements CardReaderObserver {
	
	private BigDecimal amountToPay;
	private CardIssuer cardIssuer;
	
	
	public PayWithCard(SelfCheckoutStation station, PurchasedItems items, BigDecimal amountToPay, CardIssuer cardIssuer) {
		super(station, items);
		if (amountToPay.compareTo(super.getAmountDue()) > 0) {
			this.amountToPay = super.getAmountDue();
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
	
	public void reactToCardDataReadEvent(CardReader reader, CardData data) {
		int holdNumber = cardIssuer.authorizeHold(data.getNumber(), amountToPay);
		if (holdNumber == -1) return;
		boolean transactionPosted = cardIssuer.postTransaction(data.getNumber(), holdNumber, amountToPay);
		if (transactionPosted) super.Pay(amountToPay);
	}
}
