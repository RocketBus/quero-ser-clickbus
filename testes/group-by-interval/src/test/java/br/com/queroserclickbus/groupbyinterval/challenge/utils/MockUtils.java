package br.com.queroserclickbus.groupbyinterval.challenge.utils;

import br.com.queroserclickbus.groupbyinterval.challenge.dto.CollectionNumberInput;
import br.com.queroserclickbus.groupbyinterval.challenge.dto.CollectionNumberOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class MockUtils {

	public static CollectionNumberInput.CollectionNumberInputBuilder collectionNumberByStep10() {
		return CollectionNumberInput.builder()
				.range(10)
				.numberSet(new HashSet<>(Arrays.asList(10, 1, -20, 14, 99, 136, 19, 20, 117, 22, 93, 120,
						131)));
	}

    public static CollectionNumberInput.CollectionNumberInputBuilder collectionNumberByStep15() {
        return CollectionNumberInput.builder()
                .range(15)
                .numberSet(new HashSet<>(Arrays.asList(10, 1, -20, 14, 99, 136, 19, 20, 117, 22, 93, 120,
                        131)));
    }

	public static CollectionNumberOutput collectionNumberResponseBy10() {
		ArrayList<ArrayList<Integer>> masterArrayList = new ArrayList<>();

		masterArrayList.add(new ArrayList<>() {{
			add(-20);
		}});

		masterArrayList.add(new ArrayList<>() {{
			add(1);
			add(10);
		}});

		masterArrayList.add(new ArrayList<>() {{
			add(14);
			add(19);
			add(20);
		}});

		masterArrayList.add(new ArrayList<>() {{
			add(22);
		}});

		masterArrayList.add(new ArrayList<>() {{
			add(93);
			add(99);
		}});

		masterArrayList.add(new ArrayList<>() {{
			add(117);
			add(120);
		}});

		masterArrayList.add(new ArrayList<>() {{
			add(131);
			add(136);
		}});

		return CollectionNumberOutput.builder().response(masterArrayList).build();
	}
}
