package userinterface.web;

import userinterface.web.technical.HtmlFragment;

public class DefaultPage implements HtmlFragment {

    private final HtmlFragment content;
    private final SearchBar searchBar;

    public DefaultPage(SearchBar searchBar, HtmlFragment content) {
        this.searchBar = searchBar;
        this.content = content;
    }

    @Override
    public String render() {
        return """
                <!DOCTYPE html>
                <html lang="fr-FR">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
                    <title>Nutricount</title>
                </head>
                <body>
                    <div style="display: flex; align-items: center;">
                        <h1 style="margin-right: 50px;"><a href="/?search-term=all">Nutricount</a></h1>
                        %s
                        <h1 style="margin-left: 50px;"><a href="/recettes">Recettes</a></h1>
                    </div>
                        %s
                <script>
                    window.onload = function() {
                        document.getElementById('search-term').focus();
                    };
                </script>
                </body>
                </html>
                """.formatted(searchBar.render(), content.render());
    }
}
