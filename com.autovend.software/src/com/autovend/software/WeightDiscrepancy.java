public class WeightDiscrepancy implements ElectronicScaleObserver{
    private selfCheckoutStation selfCheckoutStation;

    public WeightDiscrepancy(selfCheckoutStation selfCheckoutStation)
    {
        this.selfCheckoutStation = selfCheckoutStation;
        selfCheckoutStation.baggingArea.register(this);
    }
    @Override
    public void reactToWeightChangedEvent(ElectronicScale scale, double weightInGrams) {
        // Exception 1. Weight Discrepancy
        // stays disabled if the weight of the bagging area does not match the expected weight
            if(PurchasedItems.getTotalExpectedWeight() == weightInGrams) {
                selfCheckoutStation.handheldScanner.enable();
                selfCheckoutStation.mainScanner.enable();
            }
            else
            {
                selfCheckoutStation.handheldScanner.disable();
                selfCheckoutStation.mainScanner.disable();
                // throw exception to IO?
            }

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
    public void reactToOverloadEvent(ElectronicScale scale) {
        // TODO Auto-generated method stub
        selfCheckoutStation.handheldScanner.disable();
        selfCheckoutStation.mainScanner.disable();
        // throw exception to IO?
    }

    @Override
    public void reactToOutOfOverloadEvent(ElectronicScale scale) {
        // TODO Auto-generated method stub
            selfCheckoutStation.handheldScanner.enable();
            selfCheckoutStation.mainScanner.enable();
    }
}
