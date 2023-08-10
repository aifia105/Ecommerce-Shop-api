package com.PersonalProject.Jemo.dto;



import com.PersonalProject.Jemo.model.MvtStk;
import com.PersonalProject.Jemo.model.TypeMvtStk;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class MvtStkDto {

    private Long id;

    private Instant dateMvt;

    private BigDecimal quantity;

    private ProductDto productDto;

    private TypeMvtStk typeMvt;


    public static MvtStkDto fromEntity(MvtStk mvtStk){
        if (mvtStk == null){
            return null;
        }
        return MvtStkDto.builder()
                .id(mvtStk.getId())
                .dateMvt(mvtStk.getDateMvt())
                .quantity(mvtStk.getQuantity())
                .productDto(ProductDto.formEntity(mvtStk.getProduct()))
                .typeMvt(mvtStk.getTypeMvt()).build();
    }

    public static MvtStk toEntity(MvtStkDto mvtStkDto){
        if (mvtStkDto == null){
            return null;
        }
        MvtStk mvtStk = new MvtStk();
        mvtStk.setId(mvtStkDto.getId());
        mvtStk.setDateMvt(mvtStkDto.getDateMvt());
        mvtStk.setQuantity(mvtStkDto.getQuantity());
        mvtStk.setProduct(ProductDto.toEntity(mvtStkDto.getProductDto()));
        mvtStk.setTypeMvt(mvtStkDto.getTypeMvt());
        return mvtStk;
    }
}
