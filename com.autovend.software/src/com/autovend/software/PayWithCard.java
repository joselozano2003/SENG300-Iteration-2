package com.autovend.software;
import com.autovend.Card.CardData;
import com.autovend.devices.AbstractDevice;
import com.autovend.devices.CardReader;
import com.autovend.devices.SelfCheckoutStation;
import com.autovend.devices.observers.AbstractDeviceObserver;
import com.autovend.devices.observers.CardReaderObserver;

public class PayWithCard extends Pay implements CardReaderObserver {

	public PayWithCard(SelfCheckoutStation station, PurchasedItems items) {
		super(station, items);
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
		// TODO Auto-generated method stub
		
	}

}
