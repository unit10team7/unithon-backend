package com.team.unithonbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SingleResponseDto<T> {
    private List<T> data;
}
