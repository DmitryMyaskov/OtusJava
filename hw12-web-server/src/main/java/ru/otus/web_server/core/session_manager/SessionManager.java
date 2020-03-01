package ru.otus.web_server.core.session_manager;

public interface SessionManager extends AutoCloseable {

    void beginSession();

    void commitSession();

    void rollbackSession();

    void close();

    DatabaseSession getCurrentSession();
}
