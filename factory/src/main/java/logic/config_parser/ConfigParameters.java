package logic.config_parser;

public enum ConfigParameters {
        StorageBodyworkSize,
        StorageAccessorySize,
        StorageEngineSize,
        StorageAutoSize,
        AccessorySupplierNumber,
        DealerNumber,
        WorkerNumber,
        LogUse;


        public int getDefaultValue(ConfigParameters a) {
            switch (a) {
                case DealerNumber, AccessorySupplierNumber, WorkerNumber -> {
                    return 5;
                }
                case StorageAutoSize -> {
                    return 20;
                }
                case StorageEngineSize, StorageBodyworkSize, StorageAccessorySize -> {
                    return 10;
                }
                case LogUse -> {
                    return 1;
                }
            }
            throw new RuntimeException();
        }


}
