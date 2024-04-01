package racinggame.dto;

import racinggame.model.Car;

public final class CarDto {
    private final String name;
    private final int offset;

    public static CarDto from(Car car) {
        return new CarDto(car.name(), car.offset());
    }

    private CarDto(String name, int offset) {
        this.name = name;
        this.offset = offset;
    }

    public String status() {
        return name + " : " + "-".repeat(offset);
    }
}
