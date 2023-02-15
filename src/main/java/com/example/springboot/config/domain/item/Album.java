package com.example.springboot.config.domain.item;

import com.example.springboot.config.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@DiscriminatorValue("A") // 해당 데이터로 DiscriminatorColumn의 값을 변경할수 있다.
public class Album extends Item {

    private String artist;
}
