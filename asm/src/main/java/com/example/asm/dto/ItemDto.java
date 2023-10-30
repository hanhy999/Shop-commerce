package com.example.asm.dto;

import javax.validation.OverridesAttribute;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    int maSp;
    int soLuong;
    String title;
    double price;
    String image;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemDto other = (ItemDto) obj;
        if (this.maSp != other.maSp) {
            return false;
        }
        return true;
    }
}
