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

import com.autovend.devices.observers.AbstractDeviceObserver;
import com.autovend.software.AddItem;

public interface AddItemObserver extends AbstractDeviceObserver {

    /**
     * Announces that a barcoded product has been added to the cart.
     *
     * @param addItem
     *             The AddItem object that added the product.
     */
    void reactToAddedBarcodedProduct(AddItem addItem);

    /**
     * Announces that a non-barcoded product has been added to the cart.
     *
     * @param addItem
     *             The AddItem object that added the product.
     */

    void reactToAddedNonBarcodedProduct(AddItem addItem);


    /**
     * Announces that a product has been removed from the cart.
     *
     * @param addItem
     *             The AddItem object that removed the product.
     */
    void reactToRemovedProduct(AddItem addItem);

    /**
     * Announces that a product has been removed from the cart.
     *
     * @param addItem
     *             The AddItem object that removed the product.
     */
    void reactToRemovedAllProducts(AddItem addItem);
}
