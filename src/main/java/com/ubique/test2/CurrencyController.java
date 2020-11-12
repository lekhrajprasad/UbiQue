package com.ubique.test2;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CurrencyController {

    @PutMapping(value = "/v1.0/currencyReq", consumes = "application/json", produces = "application/json")
    public Map<String, String > getCustomerById(@RequestBody List<Currency> currencies){
        Map<String, String > map = new HashMap<>();
        for(Currency currency:currencies){
            if(map.get(currency.getCurrency()) == null){
                map.put(currency.getCurrency(),currency.getAmount());
            }else {
                String amount = map.get(currency.getCurrency());
                int amt = Integer.parseInt(amount);
                String inAmt = currency.getAmount();
                int sum = amt+Integer.parseInt(inAmt);
                map.put(currency.getCurrency(),String.valueOf(sum));
            }
        }
        return map;
    }


}
