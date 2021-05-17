package br.com.queroserclickbus.groupbyinterval.challenge.domain.service;

import br.com.queroserclickbus.groupbyinterval.challenge.dto.CollectionNumberOutput;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Range;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class GroupByIntervalService {

	public CollectionNumberOutput process(final Set<Integer> numberSet, final Integer step) {
		if (Optional.ofNullable(step).orElse(0) == 0 && numberSet.isEmpty()) {
			return CollectionNumberOutput.builder().response(new ArrayList<>()).build();
		}

		final List<Integer> sortedList = orderNumber(numberSet);
		return splitByGroup(sortedList, step);
	}

	public List<Integer> orderNumber(final Set<Integer> numberSet) {
		List<Integer> numberSetList = new ArrayList<>(numberSet);
		int aux;

		for (int i = 0; i < numberSetList.size(); i++) {
			for (int j = i + 1; j < numberSetList.size(); j++) {
				if (numberSetList.get(i) >= numberSetList.get(j)) {
					aux = numberSetList.get(i);
					numberSetList.set(i, numberSetList.get(j));
					numberSetList.set(j, aux);
				}
			}
		}

		return numberSetList;
	}

	public CollectionNumberOutput splitByGroup(final List<Integer> numberSetList, final int step) {
		ArrayList<ArrayList<Integer>> masterArrayList = new ArrayList<>();

		int masterListIndex;
		Integer lastNumber = null;

		masterArrayList.add(new ArrayList<>() {{
			add(numberSetList.get(0));
		}});

		Range<Integer> range = Range.between(numberSetList.get(0), numberSetList.get(0) + step);

		for (int index = 1; index <= numberSetList.size() - 1;) {
			if (range.contains(numberSetList.get(index)) && numberSetList.get(index) < range.getMaximum()) {

				masterListIndex = masterArrayList.size() - 1;
				ArrayList<Integer> lastListReference = masterArrayList.get(masterArrayList.size() - 1);
				lastNumber = lastListReference.size() == 0 ? 0 : lastListReference.get(lastListReference.size() - 1);

				masterArrayList.get(masterListIndex).add(lastListReference.size(), numberSetList.get(index));

				index++;
			} else {
				range = lastNumber == null ?
						Range.between(numberSetList.get(index), numberSetList.get(index) + step) :
						Range.between(range.getMaximum(), (range.getMaximum() + step));

				if (numberSetList.get(index) < range.getMaximum()) {
					masterArrayList.add(new ArrayList<>());
				}
			}
		}

		return CollectionNumberOutput.builder().response(masterArrayList).build();
	}
}
