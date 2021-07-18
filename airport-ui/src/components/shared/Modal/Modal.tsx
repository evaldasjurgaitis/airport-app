import React from 'react';
import { Modal as ModalBase } from 'antd';
import { ModalProps } from 'antd/lib/modal';

import 'antd/lib/modal/style/index.less';

interface Props extends ModalProps {
    children?: React.ReactNode;
    onClose?: () => void;
    visible: boolean;
    width?: number;
    closable?: boolean;
    maskClosable?: boolean;
}

const Modal = ({ children, onClose, visible, width = 500, closable = true, maskClosable = true }: Props) => {
    return (
        <ModalBase
            destroyOnClose
            visible={visible}
            width={width}
            onCancel={onClose}
            closable={closable}
            maskClosable={maskClosable}
            centered
            footer={false}
        >
            {children}
        </ModalBase>
    );
};

export default Modal;
