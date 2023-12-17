package br.com.nevra.acbr.domain.common;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Getter
@Setter
public abstract class ACBrLibBase implements AutoCloseable {

    protected static final Charset UTF8 = StandardCharsets.UTF_8;
    protected static final int STR_BUFFER_LEN = 256;
    private Pointer libHandler;

    @Override
    public void close() throws Exception {
        if (libHandler != null)
            dispose();
        libHandler = null;
    }

    public abstract String configLerValor(ACBrSessao eSessao, String eChave) throws Exception;

    public abstract void configGravarValor(ACBrSessao eSessao, String eChave, Object value) throws Exception;

    protected abstract void dispose() throws Exception;

    /**
     * Função para pegar o ultimo retorno da biblioteca caso o retorno seja
     * maior que o esperado, ou tenha ocorrido um erro.
     * @param buffer
     * @param bufferLen
     */
    protected abstract void UltimoRetorno(ByteBuffer buffer, IntByReference bufferLen);

    /**
     *
     * @param value
     * @return
     */
    protected static String toUTF8(String value) {
        return new String(value.getBytes(UTF8));
    }

    /**
     *
     * @param buffer
     * @param len
     * @return
     */
    protected static String fromUTF8(ByteBuffer buffer, int len) {
        return new String(buffer.array(), 0, len, UTF8);
    }

    protected static String toUTF8(Boolean value) {
        return toUTF8(value ? "1" : "0");
    }

    protected static String toUTF8(int value) {
        return toUTF8(Integer.toString(value));
    }

    protected static String toUTF8(char[] value) {
        return toUTF8(new String(value));
    }

    protected static String fromUTF8(ByteBuffer buffer, IntByReference len) {
        byte[] array = buffer.array();
        return new String(array, 0, len.getValue(), UTF8);
    }

    /**
     *
     * @param result
     * @throws Exception
     */
    protected void checkResult(int result) throws Exception {
        if (result == 0) {
            return;
        }

        ByteBuffer buffer = ByteBuffer.allocate(STR_BUFFER_LEN);
        IntByReference bufferLen = new IntByReference(STR_BUFFER_LEN);

        UltimoRetorno(buffer, bufferLen);
        throw new Exception(processResult(buffer, bufferLen));
    }

    /**
     * Função para processar o resultado do retorno da biblioteca.
     * @param buffer
     * @param bufferLen
     * @return
     */
    protected String processResult(ByteBuffer buffer, IntByReference bufferLen){
        int bLen = bufferLen.getValue();
        if (bLen > STR_BUFFER_LEN) {
            int tamanhoBuffer = (int)(Math.round(bLen * 1.3));
            ByteBuffer nBuffer = ByteBuffer.allocate(tamanhoBuffer);
            IntByReference nBufferLen = new IntByReference(tamanhoBuffer);
            UltimoRetorno(nBuffer, nBufferLen);
            return fromUTF8(nBuffer, nBufferLen);
        }

        return fromUTF8(buffer, bufferLen);
    }

}
