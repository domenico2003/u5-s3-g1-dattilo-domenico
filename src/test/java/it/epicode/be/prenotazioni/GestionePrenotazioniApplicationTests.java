package it.epicode.be.prenotazioni;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class GestionePrenotazioniApplicationTests {
	@Autowired
	MockMvc mock;

//	@Test
//	void shouldGetInfo() throws Exception {
//		mock.perform(MockMvcRequestBuilders.get("/infoprenotazioni?lingua=italiano")).andExpect(status().isOk());
//	}

}
