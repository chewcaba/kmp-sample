//
//  ContentView.swift
//  kmpsample
//
//  Created by A.J. Cabanatuan on 8/27/20.
//  Copyright © 2020 A.J. Cabanatuan. All rights reserved.
//

import SwiftUI
import iosFramework

struct ContentView: View {
    
    var greeting = Greeting()
    
    var body: some View {
        Text("Hello, World! \(greeting.message())")
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
