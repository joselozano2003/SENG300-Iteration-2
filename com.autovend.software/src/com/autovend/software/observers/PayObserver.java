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
package com.autovend.software.observers;

import com.autovend.devices.BillDispenser;
import com.autovend.devices.BillSlot;
import com.autovend.devices.observers.AbstractDeviceObserver;
import com.autovend.software.Pay;

public interface PayObserver extends AbstractDeviceObserver {

    /**
     * Announces that the customer has provided sufficient funds to cover the
     * total cost of the items in their cart.
     *
     * @param payObject
     *             The payment processor that has processed these funds.
     */
    void reactToSufficientPaymentEvent(Pay payObject);


    /**
     * Announces that a bill has been emitted as change for customer to
     * retrieve.
     *
     * @param payObject
     *             The payment processor that has processed these funds.
     * @param slot
     *             The slot from which the bill was emitted.
     */
    void reactToBillChangeProducedEvent(Pay payObject, BillSlot slot);


    /**
     * Announces that the payment processor object was unable to dispense enough
     * change due to lack of change in dispenser.
     *
     * @param payObject
     *             The payment processor that was dispensing change.
     * @param dispenser
     *             The dispenser that is out of change.
     */
    void reactToInsufficientChangeEvent(Pay payObject, BillDispenser dispenser);
}
