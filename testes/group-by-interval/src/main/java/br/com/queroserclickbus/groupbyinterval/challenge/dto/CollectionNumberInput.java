package br.com.queroserclickbus.groupbyinterval.challenge.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollectionNumberInput {

	private int range;
	private Set<Integer> numberSet;
}
