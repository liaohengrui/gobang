package com.controller;

import core.MaxMin;
import core.ValuationFunction;
import org.springframework.web.bind.annotation.*;

/**
 * Demo class
 *
 * @author HengruiLiao
 * @date 2019/1/5
 */
@RestController
public class AIContro {
    MaxMin ai = new MaxMin();
    ValuationFunction function = new ValuationFunction();

    @PostMapping(value = "/ai")
    @ResponseBody
    @CrossOrigin
    public int[] aiPoint(@RequestBody int[][] board) {
        int[] point = ai.maxmin(board, 2);
        return point;
    }

}
