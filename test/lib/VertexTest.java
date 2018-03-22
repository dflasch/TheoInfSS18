package lib;

import static org.junit.Assert.*;

import org.junit.Test;

import lib.Vertex;

public class VertexTest {

    @Test
    public void equalsDetectsNull() {
        assertEquals(new Vertex(null, null), new Vertex(null, null));
        assertEquals(new Vertex("name", null), new Vertex("name", null));
        assertEquals(new Vertex(null, "data"), new Vertex(null, "data"));
    }

    @Test
    public void equalsComparesStrings() {
        assertEquals(new Vertex("name", "data"), new Vertex("name", "data"));
        assertNotEquals(new Vertex("name", "data"), new Vertex("wrong", "data"));
        assertNotEquals(new Vertex("name", "data"), new Vertex("name", "wrong"));
        assertNotEquals(new Vertex("name", "data"), new Vertex("wrong", "wrong"));
    }

    @Test
    public void toStringEqualsId() {
        Vertex vertexToCheck = new Vertex("myname", "mydata");
        assertEquals("myname", vertexToCheck.toString());
    }

}
