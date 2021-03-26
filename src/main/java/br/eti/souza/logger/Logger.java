package br.eti.souza.logger;

import br.eti.souza.exception.SystemException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;

/**
 * Classe para log.
 * @author Alan Moraes Souza
 */
public final class Logger {

    /** SimpleDateFormat usado para horario do log. */
    private final static SimpleDateFormat FORMAT_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    /**
     * Metodo para reportar o log no System.out.
     * @param subject Assunto do log.
     * @param text Texto do log.
     */
    private static void sendToSystemOut(String subject, String text) {
        System.out.println(subject);
        if (text != null && !text.isBlank()) {
            System.out.println(text);
            System.out.println();
        }
    }

    /**
     * Junta o level do log com o tempo em que ocorreu.
     * @param level Level do log.
     * @param time Tempo do log.
     * @param title Título do log.
     * @return Level e tempo do log unidos.
     */
    private static String createSubject(String level, long time, String title) {
        return level + ":[" + Logger.FORMAT_TIME.format(time) + "]: " + title;
    }

    /**
     * Obter stacktrace da exceção como string.
     * @param exception Exceção para logar.
     * @return String com stacktrace da exceção.
     */
    private static String getStackTrace(SystemException exception) {
        String text;
        try (StringWriter out = new StringWriter()) {
            try (PrintWriter writer = new PrintWriter(out)) {
                exception.printStackTrace(writer);
            }
            text = out.getBuffer().toString();
        } catch (IOException e) {
            text = "could.not.write.stack.trace.from.system.exception: " + e.getMessage();
        }
        return text;
    }

    /**
     * Logar erro no System.out.
     * @param message Mensagem do log.
     */
    public static void error(String message) {
        Logger.error(message, null);
    }

    /**
     * Logar erro no System.out.
     * @param exception Exceção para logar.
     */
    public static void error(SystemException exception) {
        Logger.error(exception.getMessage(), Logger.getStackTrace(exception));
    }

    /**
     * Logar erro no System.out.
     * @param title Título do log.
     * @param text Texto do log.
     */
    public static void error(String title, String text) {
        String subject = Logger.createSubject("ERROR", System.currentTimeMillis(), title);
        Logger.sendToSystemOut(subject, text);
    }

    /**
     * Logar alerta no System.out.
     * @param message Mensagem do log.
     */
    public static void warning(String message) {
        Logger.warning(message, null);
    }

    /**
     * Logar alerta no System.out.
     * @param exception Exceção para logar.
     */
    public static void warning(SystemException exception) {
        Logger.warning(exception.getMessage(), Logger.getStackTrace(exception));
    }

    /**
     * Logar alerta no System.out.
     * @param title Título do log.
     * @param text Texto do log.
     */
    public static void warning(String title, String text) {
        String subject = Logger.createSubject("WARNING", System.currentTimeMillis(), title);
        Logger.sendToSystemOut(subject, text);
    }

    /**
     * Logar info no System.out.
     * @param message Mensagem do log.
     */
    public static void info(String message) {
        Logger.info(message, null);
    }

    /**
     * Logar info no System.out.
     * @param exception Exceção para logar.
     */
    public static void info(SystemException exception) {
        Logger.info(exception.getMessage(), Logger.getStackTrace(exception));
    }

    /**
     * Logar info no System.out.
     * @param title Título do log.
     * @param text Texto do log.
     */
    public static void info(String title, String text) {
        String subject = Logger.createSubject("INFO", System.currentTimeMillis(), title);
        Logger.sendToSystemOut(subject, text);
    }
}
