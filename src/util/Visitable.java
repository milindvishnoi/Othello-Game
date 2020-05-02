package ca.utoronto.utm.util;

public interface Visitable {
    void accept(Visitor v);
}
