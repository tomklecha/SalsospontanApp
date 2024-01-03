import SwiftUI
import shared

struct ContentView: View {
    let greet = Greeting().greet()
    
    @State var selectedTab : Tabs = .home
    
    
    var body: some View {
        VStack
        {
            Text(greet)
            
            Spacer()
            
            CustomTabBar(selectedTab: $selectedTab)
        }
        
    }
}
