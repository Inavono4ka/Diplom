package ru.netology.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardInfo {
    String number;
    String month;
    String year;
    String holder;
    String cvc;
}