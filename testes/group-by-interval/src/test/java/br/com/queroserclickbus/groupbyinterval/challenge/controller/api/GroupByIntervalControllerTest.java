package br.com.queroserclickbus.groupbyinterval.challenge.controller.api;


import br.com.queroserclickbus.groupbyinterval.challenge.api.controller.GroupByIntervalController;
import br.com.queroserclickbus.groupbyinterval.challenge.domain.service.GroupByIntervalService;
import br.com.queroserclickbus.groupbyinterval.challenge.dto.CollectionNumberInput;
import br.com.queroserclickbus.groupbyinterval.challenge.dto.CollectionNumberOutput;
import br.com.queroserclickbus.groupbyinterval.challenge.utils.MockUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(GroupByIntervalController.class)
public class GroupByIntervalControllerTest {

	@Autowired
	private MockMvc mvc;

	private JacksonTester<CollectionNumberOutput> jsonCollectionNumberOutput;

	@MockBean
	private GroupByIntervalService groupByIntervalServiceService;

	@Before
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	public void process() throws Exception {
		CollectionNumberInput.CollectionNumberInputBuilder collectionNumber = MockUtils.collectionNumberByStep10();

		given(groupByIntervalServiceService.process(collectionNumber.build().getNumberSet(),
				collectionNumber.build().getRange())).willReturn(MockUtils.collectionNumberResponseBy10());

		MockHttpServletResponse response = mvc.perform(post("/api/group-by-interval")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonCollectionNumberOutput.
						write(MockUtils.collectionNumberResponseBy10()).getJson()))
				.andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}
}
