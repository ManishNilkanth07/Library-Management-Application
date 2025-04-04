package com.management.library_management_system.model;

public class ParkingSlot {

    private int slotId;

    private String slotNumber;

    public ParkingSlot() {
    }

    private ParkingSlot(ParkingSlotBuilder builder) {
       
        this.slotId = builder.slotId;
        
        this.slotNumber = builder.slotNumber;
    }

    public int getSlotId() {
        return slotId;
    }

    public String getSlotNumber() {
        return slotNumber;
    }
    
    

    @Override
    public String toString() {
        return "ParkingSlot{" + "slotId=" + slotId + ", slotNumber=" + slotNumber + '}';
    }

    
    public static class ParkingSlotBuilder {

        private int slotId;

        private String slotNumber;

        public ParkingSlotBuilder setSlotId(int slotId) {
            this.slotId = slotId;
            return this;
        }

        public ParkingSlotBuilder setSlotNumber(String slotNumber) {
            this.slotNumber = slotNumber;
            return this;
        }
        
        public ParkingSlot build()
        {
            return new ParkingSlot(this);
        }
         
    }
}
