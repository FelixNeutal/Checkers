namespace CLIMenuSystem;

public class MenuItem
{
    private string _shortCut { get; set; }
    private string _title { get; set; }
    public Func<string> Function;

    public MenuItem(string shortcut, string title, Func<string> function)
    {
        _shortCut = shortcut.ToUpper();
        _title = title;
        Function = function;
    }

    public string GetShortcut()
    {
        return _shortCut;
    }

    public override string ToString()
    {
        return _shortCut + ") " + _title;
    }
}