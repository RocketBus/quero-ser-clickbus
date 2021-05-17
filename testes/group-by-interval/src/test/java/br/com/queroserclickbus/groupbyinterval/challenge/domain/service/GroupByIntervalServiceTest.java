package br.com.queroserclickbus.groupbyinterval.challenge.domain.service;

import br.com.queroserclickbus.groupbyinterval.challenge.dto.CollectionNumberInput;
import br.com.queroserclickbus.groupbyinterval.challenge.utils.MockUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GroupByIntervalServiceTest {

    @InjectMocks
    private GroupByIntervalService groupByIntervalService;

    @Test
    public void testSortedList() {
        CollectionNumberInput collectionNumberInput = MockUtils.collectionNumberByStep10().build();
        List<Integer> sortedList = collectionNumberInput.getNumberSet().stream().sorted().collect(Collectors.toList());

        assertEquals(groupByIntervalService.orderNumber(collectionNumberInput.getNumberSet()), sortedList);
    }

    @Test
    public void testGroupByInterval() {
        CollectionNumberInput collectionNumberInput = MockUtils.collectionNumberByStep10().build();
        List<Integer> sortedList = collectionNumberInput.getNumberSet().stream().sorted().collect(Collectors.toList());

        assertEquals(groupByIntervalService.splitByGroup(sortedList, 10).getResponse(),
                MockUtils.collectionNumberResponseBy10().getResponse());
    }
}
