package com.PersonalProject.Jemo.dto;


import com.PersonalProject.Jemo.model.Address;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {


    private String street;


    private String city;


    private String state;


    private String postalCode;


    private String country;

    public static AddressDto fromEntity(Address address){
        if(address == null){
            return null;
        }
        return AddressDto.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .postalCode(address.getPostalCode())
                .country(address.getCountry()).build();
    }

    public static Address toEntity(AddressDto addressDto){
        if (addressDto == null){
            return null;
        }
        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setPostalCode(addressDto.getPostalCode());
        address.setCountry((addressDto.getCountry()));
        return address;
    }
}
