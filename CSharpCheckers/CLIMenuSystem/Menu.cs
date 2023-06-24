using Console = System.Console;

namespace CLIMenuSystem;

public class Menu
{
    private List<MenuItem> _menuItems = new List<MenuItem>();
    private List<MenuItem> _specialMenuItems = new List<MenuItem>();
    private string _title { get; set; }
    private MenuLevel _level { get; set; }
    public Menu(string t, MenuLevel level)
    {
        _level = level;
        _title = t;

        if (_level == MenuLevel.FIRST)
        {
            _specialMenuItems.Add(new MenuItem("E", "Exit", null));
        }
        
        if (_level == MenuLevel.SECOND)
        {
            _specialMenuItems.Add(new MenuItem("R", "Return", null));
            _specialMenuItems.Add(new MenuItem("E", "Exit", null));
        }
    }

    public string Run()
    {
        string input = "";
        while (input != "E")
        {
            Console.Clear();
            Console.WriteLine("\n" + _title);
            foreach (MenuItem item in _menuItems)
            {
                Console.WriteLine(item);
            }

            foreach (MenuItem item in _specialMenuItems)
            {
                Console.WriteLine(item);
            }
            Console.Write("Enter your input: ");
            input = Console.ReadLine()?.ToUpper();
            if (input == null) continue;
            if (IsExit(input))
            {
                break;
            }
            if (ShortcutExists(input))
            {
                input = RunFunction(input);
            }
        }
        return input;
    }

    private bool IsExit(string shortcut)
    {
        foreach (MenuItem item in _specialMenuItems)
        {
            if (item.GetShortcut() == shortcut)
                return true;
        }
        return false;
    }

    private bool ShortcutExists(string shortcut)
    {
        foreach (MenuItem item in _menuItems)
        {
            if (item.GetShortcut() == shortcut)
                return true;
        }
        return false;
    }

    private string RunFunction(string shortcut)
    {
        string output = "";
        foreach (MenuItem item in _menuItems)
        {
            if (item.GetShortcut() == shortcut)
            {
                output = item.Function();
                break;
            }
        }

        return output;
    }

    public void AddMenuItem(MenuItem item)
    {
        _menuItems.Add(item);
    }
    
    public void AddMenuItems(List<MenuItem> items)
    {
        foreach (MenuItem item in items)
        {
            AddMenuItem(item);
        }
    }
}