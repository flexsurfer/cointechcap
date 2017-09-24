(ns cointechcap.data)

(def coins
  [{:name        "Status"
    :description "Status.im is mobile Ethereum Dapps browser, whisper messenger, wallet, and gateway to a decentralized world."
    :token-used? true
    :asset?      true
    :web         "http://status.im"
    :paper       {:en "https://status.im/whitepaper.pdf"}
    :social      {:slack    "http://slack.status.im/"
                  :facebook "https://www.facebook.com/ethstatus"
                  :twitter  "https://twitter.com/ethstatus"
                  :youtube  "https://www.youtube.com/channel/UCFzdJTUdzqyX4e9dOW7UpPQ/"
                  :reddit   "https://www.reddit.com/r/statusim/"}
    :products    [:ios :android]
    :org         "status-im"
    :platform    {:type   :eth
                  :erc20? true}}

   {:name        "EOS"
    :description "EOS.IO is software that introduces a blockchain architecture designed to enable vertical and horizontal scaling of decentralized applications (the “EOS.IO Software”)."
    :token-used? false
    :asset?      true
    :web         "https://eos.io"
    :paper       {:en "https://github.com/EOSIO/Documentation/blob/master/TechnicalWhitePaper.md"
                  :ru "https://github.com/EOSIO/Documentation/blob/master/ru-RU/TechnicalWhitePaper.md"
                  :ch "https://github.com/EOSIO/Documentation/blob/master/zh-CN/TechnicalWhitePaper.md"
                  :ko "https://github.com/EOSIO/Documentation/blob/master/ko-KR/TechnicalWhitePaper.md"}
    :social      {:steemit  "http://steemit.com/@eosio"
                  :telegram "http://eos.io/chat"
                  :facebook "https://www.facebook.com/eosblockchain"
                  :twitter  "http://twitter.com/eos_io"}
    :products    [:desktop]
    :org         "eosio"
    :platform    {:type   :eth
                  :erc20? true}}

   {:name        "Tether"
    :description "Digital money for a digital age. Tethers exists on blockchains through the Omni Protocol. Tether Platform currencies are 100% backed by actual fiat currency assets in our reserve account. Tethers are redeemable and exchangeable pursuant to Tether Limited’s terms of service. The conversion rate is 1 tether USD₮ equals 1 USD."
    :token-used? nil
    :asset?      true
    :web         "https://tether.to"
    :paper       {:en "https://tether.to/wp-content/uploads/2016/06/TetherWhitePaper.pdf"}
    :social      {:linkedin "https://www.linkedin.com/company/tether"
                  :facebook "https://www.facebook.com/tether.to"
                  :twitter  "https://twitter.com/Tether_to/"}
    :products    [:ios :android :rest]
    :platform    {:type :omni}}

   {:name        "Augur"
    :description "Augur combines the magic of prediction markets with the power of a decentralized network to create a stunningly accurate forecasting tool - and the chance for real money trading profits"
    :org         "AugurProject"
    :web         "https://augur.net/"
    :token-used? true
    :asset?      true
    :paper       {:en "https://bravenewcoin.com/assets/Whitepapers/Augur-A-Decentralized-Open-Source-Platform-for-Prediction-Markets.pdf"}
    :social      {:slack    "http://invite.augur.net/"
                  :facebook "https://www.facebook.com/augurproject"
                  :twitter  "https://twitter.com/AugurProject"
                  :youtube  "https://www.youtube.com/channel/UCnQRWIWIT8ExlegLTajjhiQ"
                  :reddit   "https://www.reddit.com/r/augur"}
    :products    [:dapp]
    :platform    {:type :eth}}

   {:name        "Veritaseum"
    :web         "http://veritas.veritaseum.com/"
    :description "Using Blockchains & Smart Contracts to Create All-Inclusive P2P Capital Markets"
    :asset?      true
    :token-used? nil
    :social      {:facebook "https://www.facebook.com/reggiemiddletonfintech/?fref=ts"
                  :twitter  "https://twitter.com/ReggieMiddleton"
                  :youtube  "https://www.youtube.com/channel/UCjVEF7ABJwyzw_8pb5frVmg"
                  :gplus    "https://plus.google.com/+ReggieMiddleton-the-Financial-Nostradamus"
                  :linkedin "https://www.linkedin.com/in/reggiemiddleton?trk=nav_responsive_tab_profile"}
    :products    [:desktop]
    :org         "veritaseum"
    :platform    {:type :eth}}

   {:name        "Golem"
    :description "Golem is a global, open sourced, decentralized supercomputer that anyone can access. It's made up of the combined power of user's machines, from personal laptops to entire datacenters."
    :asset?      true
    :token-used? true
    :web         "https://golem.network/"
    :paper       {:en "https://golem.network/doc/Golemwhitepaper.pdf"}
    :social      {:slack    "http://golemproject.org:3000/"
                  :facebook "https://www.facebook.com/golemproject/"
                  :twitter  "https://twitter.com/golemproject"
                  :reddit   "https://www.reddit.com/r/GolemProject/"}
    :products    [:desktop]
    :org         "golemfactory"
    :platform    {:type :eth}}

   {:name        "Gnosis"
    :description "Based on Ethereum — The next generation blockchain network. Speculate on anything with an easy-to-use prediction market."
    :web         "https://gnosis.pm/"
    :asset?      true
    :token-used? true
    :social      {:slack    "https://slack.gnosis.pm/"
                  :facebook "https://www.facebook.com/Gnosis.pm/"
                  :twitter  "https://twitter.com/gnosisPM"
                  :reddit   "https://www.reddit.com/r/gnosisPM/"
                  :medium   "https://medium.com/gnosis-pm"}
    :paper       {:en "https://gnosis.pm/resources/default/pdf/gnosis_whitepaper.pdf"}
    :products    [:dapp]
    :org         "gnosis"
    :platform    {:type :eth}}

   {:name        "Iconomi"
    :description "The ICONOMI Digital Assets Management Platform is a new and unique technical service that allows anyone from beginners to blockchain experts to invest and manage digital assets."
    :web         "https://www.iconomi.net/"
    :asset?      true
    :token-used? nil
    :paper       {:en "https://coss.io/documents/white-papers/iconomi.pdf"}
    :social      {:slack    "https://iconominet.slack.com/"
                  :facebook "https://www.facebook.com/iconomi.net"
                  :twitter  "https://twitter.com/iconominet"
                  :reddit   "https://www.reddit.com/r/ICONOMI"
                  :medium   "https://medium.com/iconominet"
                  :linkedin "https://www.linkedin.com/company/iconomi-the-financial-services-for-decentralised-economy"}
    :products    [:dapp]
    :platform    {:type :eth}}

   {:name        "MaidSafeCoin"
    :web         "https://maidsafe.net/"
    :asset?      true
    :token-used? true
    :description "The SAFE (Secure Access For Everyone) Network is made up of the unused hard drive space, processing power and data connection of its users. "
    :paper       {:en "https://github.com/maidsafe/Whitepapers"}
    :products    [:web]
    :social      {:slack   "https://slack.safenetwork.org/"
                  :twitter "https://twitter.com/maidsafe"
                  :reddit  "https://www.reddit.com/r/maidsafe"
                  :youtube "https://www.youtube.com/channel/UChDck5R_C9i6XTrS66tbwOw"}
    :org         "maidsafe"
    :platform    {:type :omni}}

   {:name     "MCAP"
    :org      "BitcoingGrowthFund"
    :platform {:type :eth}}

   {:name     "DigixDAO"
    :org      "digixglobal"
    :platform {:type :eth}}

   {:name  "Bitcoin"
    :org   "bitcoin"
    :coin? true}

   {:name  "Ethereum"
    :org   "ethereum"
    :coin? true}

   {:name  "Ripple"
    :coin? true}

   {:name  "Litecoin"
    :org   "litecoin-project"
    :fork  :btc
    :coin? true}

   {:name  "Ethereum Classic"
    :org   "ethereumproject"
    :fork  :eth
    :coin? true}

   {:name  "Dash"
    :org   "dashpay"
    :coin? true}

   {:name  "NEM"
    :org   "NemProject"
    :coin? true}

   {:name  "Monero"
    :user  "monero-project"
    :coin? true}

   {:name  "IOTA"
    :org   "iotaledger"
    :coin? true}

   {:name  "BitConnect"
    :user  "bitconnectcoin"
    :coin? true}])



