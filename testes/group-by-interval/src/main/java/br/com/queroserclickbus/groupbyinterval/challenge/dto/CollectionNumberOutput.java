package br.com.queroserclickbus.groupbyinterval.challenge.dto;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollectionNumberOutput {

	private ArrayList<ArrayList<Integer>> response;
}
