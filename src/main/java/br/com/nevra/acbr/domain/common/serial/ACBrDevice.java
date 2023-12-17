package br.com.nevra.acbr.domain.common.serial;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ACBrDevice {
    private String porta;
    private int baud;
    private int dataBits;
    private SerialParity parity;
    private SerialStopBytes stopBits;
    private SerialHandShake handShake;
    private boolean hardFlow;
    private boolean softFlow;
    private int timeOut;
    private int maxBandwidth;
    private int sendBytesCount;
    private int sendBytesInterval;

    public ACBrDevice() {
        porta = "";
        baud = 9600;
        dataBits = 8;
        parity = SerialParity.None;
        stopBits = SerialStopBytes.One;
        handShake = SerialHandShake.Nenhum;
        hardFlow = false;
        softFlow = false;
        maxBandwidth = 0;
        sendBytesInterval = 0;
        sendBytesCount = 0;
    }

    public ACBrDevice(String deviceParams) {
        this();
        if (deviceParams.isEmpty()) return;

        LerDeviceParams(deviceParams);
    }

    public void LerDeviceParams(String deviceParams){
        String[] dParams = deviceParams.split("");
        for (String dParam : dParams)
        {
            String[] parameters = dParam.toUpperCase().split("=");

            switch (parameters[0])
            {
                case "BAUD":
                    baud = Integer.parseInt(parameters[1]);
                    break;

                case "DATA":
                    dataBits = Integer.parseInt(parameters[1]);
                    break;

                case "PARITY":
                    parity = SerialParity.valueOf(parameters[1].charAt(0));
                    break;

                case "STOP":
                    stopBits = parameters[1].trim() == "1" ? SerialStopBytes.One :
                            parameters[1].trim() == "1,5" ? SerialStopBytes.OnePointFive : SerialStopBytes.Two;
                    break;

                case "HARDFLOW":
                    hardFlow = true;
                    break;

                case "SOFTFLOW":
                    softFlow = true;
                    break;

                case "HANDSHAKE":
                    handShake = parameters[1].trim() == "XON/XOFF" ? SerialHandShake.XON_XOFF :
                            parameters[1].trim() == "DTR/DSR" ? SerialHandShake.DTR_DSR :
                                    parameters[1].trim() == "RTS/CTS" ? SerialHandShake.RTS_CTS : SerialHandShake.Nenhum;
                    break;

                case "MAXBANDWIDTH":
                    maxBandwidth = Integer.parseInt(parameters[1]);
                    break;

                case "SENDBYTESCOUNT":
                    sendBytesCount = Integer.parseInt(parameters[1]);
                    break;

                case "SENDBYTESINTERVAL":
                    sendBytesInterval = Integer.parseInt(parameters[1]);
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder parmsString = new StringBuilder();

        parmsString.append(String.format("BAUD=%1$d ", baud));
        parmsString.append(String.format("DATA=%1$d ", dataBits));
        parmsString.append(String.format("PARITY=%1s ", parity.asChar()));

        parmsString.append(String.format("STOP=%1s", stopBits == SerialStopBytes.One ? "1" :
                stopBits == SerialStopBytes.OnePointFive ? "1,5" : "2"));

        parmsString.append(String.format("HANDSHAKE=%1s", handShake == SerialHandShake.XON_XOFF ? "XON/XOFF" :
                handShake == SerialHandShake.DTR_DSR ? "DTR/DSR" :
                        handShake == SerialHandShake.RTS_CTS ? "RTS/CTS" : ""));

        if (hardFlow) parmsString.append("HARDFLOW ");
        if (softFlow) parmsString.append("SOFTFLOW ");

        parmsString.append(String.format("MAXBANDWIDTH=%1$d ", maxBandwidth));
        parmsString.append(String.format("SENDBYTESCOUNT=%1$d ", sendBytesCount));
        parmsString.append(String.format("SENDBYTESINTERVAL={%1$d ", sendBytesInterval));

        return parmsString.toString().trim();
    }
}
