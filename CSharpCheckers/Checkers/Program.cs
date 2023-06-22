using CLIMenuSystem;

namespace Checkers;

public class Program
{
    public static void Main(String[] args)
    {
        Menu primaryMenu = new Menu("Primary", MenuLevel.FIRST);
        primaryMenu.AddMenuItem(new MenuItem("A", "Alpha", AlphaFunction));
        primaryMenu.AddMenuItem(new MenuItem("B", "Bravo", BravoFunction));
        primaryMenu.Run();
    }

    public static string AlphaFunction()
    {
        Console.WriteLine("Doing something important in alphafunction");
        return "";
    }
    
    public static string BravoFunction()
    {
        Menu bravoMenu = new Menu("Bravo menu", MenuLevel.SECOND);
        bravoMenu.AddMenuItem(new MenuItem("c", "Charlie", CharlieFunction));
        bravoMenu.Run();
        return "";
    }
    
    public static string CharlieFunction()
    {
        Console.WriteLine("Inside Charlie function doing something");
        return "";
    }
    
    public static string DeltaFunction()
    {
        return "";
    }
}