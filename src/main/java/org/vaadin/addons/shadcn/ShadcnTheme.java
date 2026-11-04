package org.vaadin.addons.shadcn;

/**
 * Entry point for the shadcn-vaadin theme.
 * <p>
 * The theme is a plain stylesheet built on top of Vaadin's Aura theme, so it is
 * applied the same way Aura itself is: with a {@code @StyleSheet} annotation on
 * the application's {@code AppShellConfigurator}. Load it <em>before</em> the
 * application's own stylesheets so those can override it:
 *
 * <pre>{@code
 * @StyleSheet(ShadcnTheme.STYLESHEET)
 * @StyleSheet("styles.css")
 * public class Application implements AppShellConfigurator {
 * }
 * }</pre>
 *
 * Do not also load {@code Aura.STYLESHEET}; this stylesheet imports Aura itself.
 * <p>
 * Dark mode follows the CSS {@code color-scheme} property, exactly as Aura does.
 * Switch it with {@code @ColorScheme} on the app shell or
 * {@code UI.getCurrent().getPage().setColorScheme(...)} at runtime. For
 * stylesheets ported from a shadcn project, a {@code dark} class on
 * {@code <html>} is honoured as well.
 * <p>
 * Every colour is exposed as a {@code --shadcn-*-light} / {@code --shadcn-*-dark}
 * pair (for example {@code --shadcn-primary-light}); override those on
 * {@code html} to re-theme. The unsuffixed {@code --shadcn-*} properties resolve
 * through {@code light-dark()} and are meant to be read, not set.
 */
public final class ShadcnTheme {

    /**
     * Path of the theme's master stylesheet, for use with {@code @StyleSheet}.
     */
    public static final String STYLESHEET = "shadcn-vaadin/styles.css";

    private ShadcnTheme() {
    }
}
