package com.example.asm.dto;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    ArrayList<ItemDto> carts = new ArrayList<>();

    public boolean add(ItemDto item) {
        try {
            // update
            if (carts.contains(item)) {
                ItemDto current = carts.get(carts.indexOf(item));
                current.setSoLuong(current.getSoLuong() + 1);
            } else {
                // tao moi
                carts.add(item);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean add(ItemDto item, int so) {
        try {
            System.out.println("id" + item.getMaSp());
            if (carts.contains(item)) {
                // item hien tai
                System.out.println("go here for update");
                ItemDto currentItem = carts.get(carts.indexOf(item));
                currentItem.setSoLuong(currentItem.getSoLuong() + so);
                // update
            } else {
                System.out.println("go here for create");
                carts.add(item);
                // add
            }
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean remove(ItemDto item) {
        try {
            // kt tồn tại
            if (carts.contains(item)) {
                int vt = carts.indexOf(item);
                carts.remove(vt);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // tính tiền
    public double total() {
        double sum = 0;
        for (ItemDto cart : carts) {
            sum += cart.getPrice() * cart.getSoLuong();
        }
        return sum;
    }

    // lấy số lượng item trong cart
    public int size() {
        return carts != null ? carts.size() : 0;
    }

    public boolean dete(ItemDto item) {
        try {
            if (carts.contains(item)) {
                ItemDto currentItem = carts.get(carts.indexOf(item));
                currentItem.setSoLuong(currentItem.getSoLuong() - 1);
                if (currentItem.getSoLuong() == 0) {
                    // currentItem.setSoLuong(1);
                    carts.remove(item);
                }

            } else {
                carts.remove(item);
            }
            return true;

        } catch (Exception e) {
            return false;
        }
    }

}
