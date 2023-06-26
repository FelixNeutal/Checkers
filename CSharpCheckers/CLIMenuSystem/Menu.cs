using System.Collections;
using Console = System.Console;

namespace CLIMenuSystem;

public class Menu
{
    private ConsoleColor _defaultUnselectedColor = ConsoleColor.Red;
    private ConsoleColor _defaultSelectedColor = ConsoleColor.Yellow;
    private List<int> _menuYPositions = new List<int>();
    //private int MinEdge;
    //private int MaxEdge;
    private List<MenuItem> _menuItems = new List<MenuItem>();
    private List<MenuItem> _specialMenuItems = new List<MenuItem>();
    private Hashtable _menuTable = new Hashtable();
    private string _title { get; set; }
    private MenuLevel _level { get; set; }
    public Menu(string title, MenuLevel level)
    {
        _level = level;
        _title = title;

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
        var topPos = (0, 0);
        while (input != "E")
        {
            Console.Clear();
            Console.ForegroundColor = _defaultUnselectedColor;
            Console.WriteLine(_title);
            Console.ForegroundColor = _defaultUnselectedColor;
            foreach (MenuItem item in _menuItems)
            {
                topPos = Console.GetCursorPosition();
                if (!_menuYPositions.Contains(topPos.Item2) && ! _menuTable.Contains(topPos.Item2)) {
                    _menuYPositions.Add(topPos.Item2);
                    _menuTable.Add(topPos.Item2, item);
                }
                Console.WriteLine(item);
            }

            foreach (MenuItem item in _specialMenuItems)
            {
                topPos = Console.GetCursorPosition();
                //if (!_menuYPositions.Contains(topPos.Item2))
                //    _menuYPositions.Add(topPos.Item2);
                //_menuTable.Add(topPos.Item2, item);
                if (!_menuYPositions.Contains(topPos.Item2) && ! _menuTable.Contains(topPos.Item2)) {
                    _menuYPositions.Add(topPos.Item2);
                    _menuTable.Add(topPos.Item2, item);
                }
                Console.WriteLine(item);
            }
            //Console.Write("Enter your input: ");
            //input = Console.ReadLine()?.ToUpper();
            //if (input == null) continue;
            input = ((MenuItem) _menuTable[GetSelectedMenuNumber()]).GetShortcut();
            //Console.WriteLine("Input was " + input);
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

    private int GetSelectedMenuNumber()
    {
        Console.CursorVisible = false;
        int CurrentPos = 0;
        int PreviousPos = 0;
        int MinEdge = 0;
        int MaxEdge = _menuYPositions.Count - 1;
        Boolean IsEnterPressed = false;
        while (!IsEnterPressed)
        {
            Console.SetCursorPosition(0, _menuYPositions[PreviousPos]);
            Console.ForegroundColor = _defaultUnselectedColor;
            Console.Write(_menuTable[_menuYPositions[PreviousPos]]);
            Console.SetCursorPosition(0, _menuYPositions[CurrentPos]);
            Console.ForegroundColor = _defaultSelectedColor;
            Console.Write(_menuTable[_menuYPositions[CurrentPos]]);
            Thread.Sleep(500);
            ConsoleKeyInfo key = Console.ReadKey();
            switch (key.Key)
            {
                case ConsoleKey.UpArrow:
                    PreviousPos = CurrentPos;
                    if (CurrentPos > MinEdge)
                    {
                        CurrentPos--;
                    }
                    else
                    {
                        CurrentPos = MaxEdge;
                    }

                    break;
                case ConsoleKey.DownArrow:
                    PreviousPos = CurrentPos;
                    if (CurrentPos < MaxEdge)
                    {
                        CurrentPos++;
                    }
                    else
                    {
                        CurrentPos = MinEdge;
                    }

                    break;
                case ConsoleKey.Enter:
                    IsEnterPressed = true;
                    break;
            }
        }

        //return CurrentPos;
        return _menuYPositions[CurrentPos];
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