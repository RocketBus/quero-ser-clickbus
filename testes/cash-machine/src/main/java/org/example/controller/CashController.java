package org.example.controller;

import org.example.model.WithdrawRequest;
import org.example.model.WithdrawResponse;
import org.example.service.CashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/atm/withdraw")

public class CashController {
    @Autowired
    private CashService cashService;

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping

    public List<String> withdraw(@RequestBody WithdrawRequest withdraw) throws Exception {
        List<WithdrawResponse> withdrawResponses = cashService.processWithdraw(withdraw.getAmount());
        List<String> note = new ArrayList<>();
        for (WithdrawResponse withdrawResponse : withdrawResponses) {
            note.add("Total da " + withdrawResponse.getAmountNotes() + " cedula de R$" + withdrawResponse.getNote());
        }
        return note;
    }
}

