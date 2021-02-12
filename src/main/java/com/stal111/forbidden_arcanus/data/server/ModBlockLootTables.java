package com.stal111.forbidden_arcanus.data.server;

import com.stal111.forbidden_arcanus.ForbiddenArcanus;
import net.valhelsia.valhelsia_core.data.ValhelsiaBlockLootTables;

/**
 * Mod Block Loot Tables
 * Forbidden Arcanus - com.stal111.forbidden_arcanus.data.server.ModBlockLootTables
 *
 * @author stal111
 * @version 16.2.0
 * @since 2021-02-12
 */
public class ModBlockLootTables extends ValhelsiaBlockLootTables {

    public ModBlockLootTables() {
        super(ForbiddenArcanus.REGISTRY_MANAGER);
    }

    @Override
    public void addTables() {
        forEach(this::registerDropSelfLootTable);
    }
}
