package pl.kkubiak.concise.requests;

import javax.validation.constraints.NotBlank;

public class Cache {
    @NotBlank(message = "Key is mandatory")
    private String key;
    @NotBlank(message = "Value is mandatory")
    private String value;

    public Cache(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
