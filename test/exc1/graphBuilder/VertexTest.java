package exc1.graphBuilder;

import static org.junit.Assert.*;

import org.junit.Test;

public class VertexTest {

	@Test
	public void equalsDetectsNull() {
		assertEquals(new Vertex(null,null),new Vertex(null,null));
		assertEquals(new Vertex("name",null),new Vertex("name",null));
		assertEquals(new Vertex(null,"data"),new Vertex(null,"data"));
	}
	
	@Test
	public void equalsComparesStrings() {
		assertEquals(new Vertex("name","data"),new Vertex("name","data"));
		assertNotEquals(new Vertex("name","data"),new Vertex("wrong","data"));
		assertNotEquals(new Vertex("name","data"),new Vertex("name","wrong"));
		assertNotEquals(new Vertex("name","data"),new Vertex("wrong","wrong"));
	}

}