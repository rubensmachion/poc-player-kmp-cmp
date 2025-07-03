//
//  StartPlayerView.swift
//  iosApp
//
//  Created by Rubens Machion on 02/07/25.
//

import SwiftUI
import AVKit

struct StartPlayerView: View {
    var body: some View {
        VideoPlayer(url: "https://www.w3schools.com/html/mov_bbb.mp4")
    }
}

#Preview {
    StartPlayerView()
}

@objc public class SwiftUIPlayerLauncher: NSObject {
    @objc public static func presentPlayer(withUrl url: String) {
        guard let windowScene = UIApplication.shared.connectedScenes.first as? UIWindowScene,
              let rootVC = windowScene.windows.first?.rootViewController else { return }
        
        let playerView = VideoPlayer(url: url)
        let hosting = UIHostingController(rootView: playerView)
        rootVC.present(hosting, animated: true)
    }
}

public struct VideoPlayer: View {
    let url: String
    let player: AVPlayer

    public init(url: String) {
        self.url = url
        player = AVPlayer(url: URL(string: url)!)
    }
    
    public var body: some View {
        AVKit.VideoPlayer(player: player)
            .onAppear {
                player.play()
            }
    }
}
