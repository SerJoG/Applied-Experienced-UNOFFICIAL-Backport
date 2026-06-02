@EmiEntrypoint
public class AppexEmiPlugin implements EmiPlugin {
    @Override
    public void register(EmiRegistry registry) {
        EmiStackConverters.register(new EmiExperienceStackConverter());
    }
}