package com.PersonalProject.Jemo.dto;





import com.PersonalProject.Jemo.model.Supplier;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SupplierDto {

    private Long id;

    private String name;

    private AddressDto address;

    private String mail;

    private String phone;

    private List<OrderSupplierDto> orderSupplierDtos;

    public static SupplierDto fromEntity(Supplier supplier){
        if (supplier == null){
            return null;
        }
        return SupplierDto.builder()
                .id(supplier.getId())
                .name(supplier.getName())
                .address(AddressDto.fromEntity(supplier.getAddress()))
                .mail(supplier.getMail())
                .phone(supplier.getPhone()).build();
    }

    public static Supplier toEntity(SupplierDto supplierDto) {
        if (supplierDto == null) {
            return null;
        }
        Supplier supplier = new Supplier();
        supplier.setId(supplierDto.getId());
        supplier.setName(supplierDto.getName());
        supplier.setAddress(AddressDto.toEntity(supplierDto.getAddress()));
        supplier.setMail(supplierDto.getMail());
        supplier.setPhone(supplierDto.getPhone());
        return supplier;
    }

}
