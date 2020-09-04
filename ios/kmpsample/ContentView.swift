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
    @State var username = ""
    @State var password = ""
    
    var body: some View {
        VStack() {
            Spacer()
            Text("Welcome, Please Login")
                .foregroundColor(Color.white)
            TextField("Username", text: self.$username)
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .padding(.horizontal, 20)
                .padding(.vertical, 5)
            TextField("Password", text: self.$password)
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .padding(.horizontal, 20)
                .padding(.vertical, 5)
            Button(action: {
                    
            }, label: {
                Text("Login")
            }).frame(minWidth: 0, maxWidth: .infinity)
                .padding(10)
                .background(Color.green)
                .foregroundColor(Color.white)
                .cornerRadius(30)
                .padding(.horizontal, 20)
            Spacer()
        }.frame(minWidth: 0,
                maxWidth: .infinity,
                minHeight: 0,
                maxHeight: .infinity,
                alignment: .topLeading
        ).background(Color.purple)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
