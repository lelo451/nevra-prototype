package br.com.nevra.acbr.domain.common;

/**
 *
 * @author rften
 * @param <TLib>
 */
public abstract class ACBrLibConfig<TLib extends ACBrLibBase> extends ACBrLibConfigBase<TLib> {

    private final PrincipalConfig<TLib> principal;
    private final SistemaConfig<TLib> sistema;

    public ACBrLibConfig(TLib acbrlib, ACBrSessao sessao) {
        super(acbrlib, sessao);

        principal = new PrincipalConfig<>(acbrlib);
        sistema = new SistemaConfig<>(acbrlib);
    }

    public PrincipalConfig<TLib> getPrincipal(){
        return principal;
    }

    public SistemaConfig<TLib> getSistema(){
        return sistema;
    }
}
