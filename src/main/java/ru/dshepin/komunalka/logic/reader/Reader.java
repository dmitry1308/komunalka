package ru.dshepin.komunalka.logic.reader;

public interface Reader<V,T> {
	V read(T fileName);
}
