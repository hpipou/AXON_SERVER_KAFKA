package com.kafka.axon.command.command;


import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductCreateCommand extends BaseCommand<String> {

    private String name;
    private String description;
    private Long price;
    private Long stock;

    @Builder
    public ProductCreateCommand(String id, String name, String description, Long price, Long stock) {
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
}