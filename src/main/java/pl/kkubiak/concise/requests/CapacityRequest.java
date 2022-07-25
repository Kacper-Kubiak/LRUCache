package pl.kkubiak.concise.requests;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CapacityRequest {
    @NotNull
    @Min(1)
    int capacity;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
