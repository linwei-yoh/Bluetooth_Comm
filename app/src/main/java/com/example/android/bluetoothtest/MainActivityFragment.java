package com.example.android.bluetoothtest;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String TAG = MainActivityFragment.class.getSimpleName();

    // Intent request codes
    private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;
    private static final int REQUEST_CONNECT_DEVICE_INSECURE = 2;
    private static final int REQUEST_ENABLE_BT = 3;

    @BindView(R.id.BT_Enable)
    Button mBTEnable;
    @BindView(R.id.BT_Visible)
    Button mBTVisible;
    @BindView(R.id.BT_Search)
    Button mBTSearch;
    @BindView(R.id.BT_SearchNew)
    Button mBTSearchNew;
    @BindView(R.id.BT_Contented_List)
    ListView mBTContentedList;
    @BindView(R.id.EmptyMsg)
    TextView mEmptyMsg;

    private BluetoothAdapter mBluetoothAdapter = null;
    private BluetoothChatService mChatService = null;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        mBTContentedList.setEmptyView(mEmptyMsg);

        // 获取蓝牙适配器
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // If the adapter is null, then Bluetooth is not supported
        if (mBluetoothAdapter == null) {
            FragmentActivity activity = getActivity();
            Toast.makeText(activity, "不支持蓝牙", Toast.LENGTH_LONG).show();
            activity.finish();
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        // 如果蓝牙没有开启则先启用 再在onActivityResult
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
            // Otherwise, setup the chat session
        } else if (mChatService == null) {
//            setupChat();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CONNECT_DEVICE_SECURE:
                // When DeviceListActivity returns with a device to connect
                if (resultCode == Activity.RESULT_OK) {
//                    connectDevice(data, true);
                }
                break;
            case REQUEST_CONNECT_DEVICE_INSECURE:
                // When DeviceListActivity returns with a device to connect
                if (resultCode == Activity.RESULT_OK) {
//                    connectDevice(data, false);
                }
                break;
            case REQUEST_ENABLE_BT:
                // When the request to enable Bluetooth returns
                if (resultCode == Activity.RESULT_OK) {
//                    setupChat();
                } else {
                    Toast.makeText(getActivity(), R.string.bt_not_enabled_leaving,
                            Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }
        }
    }


    @OnClick({R.id.BT_Enable, R.id.BT_Visible, R.id.BT_Search, R.id.BT_SearchNew})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.BT_Enable:
                break;
            case R.id.BT_Visible:
                break;
            case R.id.BT_Search:
                break;
            case R.id.BT_SearchNew:
                break;
        }
    }
}
